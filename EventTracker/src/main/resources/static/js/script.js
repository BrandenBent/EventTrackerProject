console.log("TESTING");

window.addEventListener('load', function(e) {
	console.log('document loaded');
	init();
});

function init() {
	console.log("test");
	document.mealForm.lookup.addEventListener("click", function(e){
		e.preventDefault();
		let mealId = document.mealForm.mealId.value;
		if (!isNaN(mealId) && mealId > 0) {
			getMeal(mealId);
		}
	});
	document.mealDeleteForm.find.addEventListener("click", function(e){
		e.preventDefault();
		let mealId = document.mealForm.mealId.value;
		if (!isNaN(mealId) && mealId > 0) {
			deleteMeal(mealId);
		}

	});

	var button = document.addForm.submit;
	button.addEventListener("click", function(e){
		e.preventDefault();
		let form = document.addForm;
		// console.log("before json: " + meal);
		var meal = {
			name: form.name.value,
			calories: form.calories.value,
			meal: form.meal.value,
			issues: form.issues.value
		};
		createMeal(meal);
	});
	//ADD MEAL GOES HERE, eventlistener etc
}

function getMeal(mealId){
	var xhr = new XMLHttpRequest();

	xhr.open('GET', 'api/meal/' + mealId, true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			var data = JSON.parse(xhr.responseText);
			displayMeal(data);
			console.log(data);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
		}
	};
	xhr.send(null);
}

function displayMeal(meal){
	var mealDiv = document.getElementById('mealData');
	mealDiv.textContent = '';

	let p = document.createElement('h1');
	p.textContent = meal.name;
	mealDiv.appendChild(p);
	let ul = document.createElement('ul');
	mealDiv.appendChild(ul);

	let li = document.createElement('li');
	li.textContent = "Calories: " + meal.calories;
	ul.appendChild(li);

	li = document.createElement('li');
	li.textContent = "Meal: " + meal.meal;
	ul.appendChild(li);

	li = document.createElement('li');
	li.textContent = meal.issues;
	ul.appendChild(li);

}

function deleteMeal(mealId) {
	var xhr = new XMLHttpRequest();

	xhr.open('DELETE', 'api/meal/' + mealId, true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			var data = JSON.parse(xhr.responseText);
			console.log(data);
			// delete(data);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
		}
	};
	xhr.send(null);
}

function createMeal(meal){

	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/meal', true);

	xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON request body

	xhr.onreadystatechange = function() {
		console.log(xhr.readyState);
  if (xhr.readyState === 4 ) {
    if ( xhr.status < 400 ) { // Ok or Created
      meal = JSON.parse(xhr.responseText);

    }
    else {
      console.log("POST request failed.");
      console.error(xhr.status + ': ' + xhr.responseText);
    }
  }
};
console.log(meal);
var json = JSON.stringify(meal); // Convert JS object to JSON string

xhr.send(json);
}
