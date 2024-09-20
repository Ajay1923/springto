function validateForm(event) {
    const idField = document.getElementById('id');
    const noOfDaysField = document.getElementById('noOfDays');
    const nameField = document.getElementById('name');

    let isValid = true;
    let errors = '';

    // Validate ID field
    if (isNaN(idField.value) || idField.value <= 0) {
        isValid = false;
        errors += 'ID must be a positive number.\n';
    }

    // Validate Name field
    if (!isNaN(nameField.value)) {
        isValid = false;
        errors += 'Name must be a text value.\n';
    }

    // Validate No of Days field
    if (isNaN(noOfDaysField.value) || noOfDaysField.value <= 0) {
        isValid = false;
        errors += 'No of Days must be a positive number.\n';
    }

    if (!isValid) {
        alert(errors);
        event.preventDefault();
    }
}
