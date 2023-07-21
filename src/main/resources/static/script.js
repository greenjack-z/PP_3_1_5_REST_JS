async function buttonClick() {
    let url = "/api/user";
    let response = await(fetch(url));
    let user = await response.json();
    addRow(user)
}

function addRow(user) {
    tBody = document.getElementById("table").getElementsByTagName("tbody")[0];

    row = document.createElement("tr");

    cellID = document.createElement("td");
    cellFirst = document.createElement("td");
    cellLast = document.createElement("td");
    cellAge = document.createElement("td");
    cellEmail = document.createElement("td");
    cellRole = document.createElement("td");
    cellEdit = document.createElement("td");
    cellDelete = document.createElement("td");

    textID = document.createTextNode(user.id);
    textFirst = document.createTextNode(user.firstname);
    textLast = document.createTextNode(user.lastname);
    textAge = document.createTextNode(user.age);
    textEmail = document.createTextNode(user.email);
    textRoles = document.createTextNode(user.roles);

    buttonEdit = document.createElement("input");
    buttonEdit.type = "button";
    buttonEdit.classList.add("btn", "btn-info");
    buttonEdit.value = "Edit";

    buttonDelete = document.createElement("input");
    buttonDelete.type = "button";
    buttonDelete.classList.add("btn", "btn-danger");
    buttonDelete.value = "Delete";

    cellID.appendChild(textID);
    cellFirst.appendChild(textFirst);
    cellLast.appendChild(textLast);
    cellAge.appendChild(textAge);
    cellEmail.appendChild(textEmail);
    cellRole.appendChild(textRoles);
    cellEdit.appendChild(buttonEdit);
    cellDelete.appendChild(buttonDelete);

    row.appendChild(cellID);
    row.appendChild(cellFirst);
    row.appendChild(cellLast);
    row.appendChild(cellAge);
    row.appendChild(cellEmail);
    row.appendChild(cellRole);
    row.appendChild(cellEdit);
    row.appendChild(cellDelete);

    tBody.appendChild(row);
}