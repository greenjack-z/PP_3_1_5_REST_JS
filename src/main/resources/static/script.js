window.onload = async function() {
    let response = await(fetch("/api/users"));
    let users = await response.json();
    users.forEach(item => addRow(item));

    response = await(fetch("/api/user"));
    let user = await response.json();
    addShortRow(user);
}

async function deleteUserRequest(id) {
    let response = await(fetch("/api/" + id, {method: "DELETE"}));
    console.log(response.status);
    if (response.ok) {
        let row = document.getElementById("r" + id);
        row.parentNode.removeChild(row);
    }
}

function addRow(user) {
    let tBody = document.getElementById("table").getElementsByTagName("tbody")[0];

    let row = document.createElement("tr");
    row.id = "r" + user.id;

    let cellID = document.createElement("td");
    let cellFirst = document.createElement("td");
    let cellLast = document.createElement("td");
    let cellAge = document.createElement("td");
    let cellEmail = document.createElement("td");
    let cellRole = document.createElement("td");
    let cellEdit = document.createElement("td");
    let cellDelete = document.createElement("td");

    let textID = document.createTextNode(user.id);
    let textFirst = document.createTextNode(user.firstname);
    let textLast = document.createTextNode(user.lastname);
    let textAge = document.createTextNode(user.age);
    let textEmail = document.createTextNode(user.email);
    let rolesList = user.roles.map(role => role.authority);
    let textRoles = document.createTextNode(rolesList.map(string => string.replace("ROLE_", "")));


    let buttonEdit = document.createElement("input");
    buttonEdit.type = "button";
    buttonEdit.classList.add("btn", "btn-info");
    buttonEdit.id = "e" + user.id;
    buttonEdit.value = "Edit";
    buttonEdit.onclick = function() {
//            deleteUserRequest(user.id);
    };

    let buttonDelete = document.createElement("input");
    buttonDelete.type = "button";
    buttonDelete.classList.add("btn", "btn-danger");
    buttonDelete.id = "d" + user.id;
    buttonDelete.value = "Delete";
    buttonDelete.onclick = function() {
        deleteUserRequest(user.id);
    };

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

function addShortRow(user) {
    let tBody = document.getElementById("userTable").getElementsByTagName("tbody")[0];

    let row = document.createElement("tr");
    row.id = "r" + user.id;

    let cellID = document.createElement("td");
    let cellFirst = document.createElement("td");
    let cellLast = document.createElement("td");
    let cellAge = document.createElement("td");
    let cellEmail = document.createElement("td");
    let cellRole = document.createElement("td");

    let textID = document.createTextNode(user.id);
    let textFirst = document.createTextNode(user.firstname);
    let textLast = document.createTextNode(user.lastname);
    let textAge = document.createTextNode(user.age);
    let textEmail = document.createTextNode(user.email);
    let rolesList = user.roles.map(role => role.authority);
    let textRoles = document.createTextNode(rolesList.map(string => string.replace("ROLE_", "")));

    cellID.appendChild(textID);
    cellFirst.appendChild(textFirst);
    cellLast.appendChild(textLast);
    cellAge.appendChild(textAge);
    cellEmail.appendChild(textEmail);
    cellRole.appendChild(textRoles);

    row.appendChild(cellID);
    row.appendChild(cellFirst);
    row.appendChild(cellLast);
    row.appendChild(cellAge);
    row.appendChild(cellEmail);
    row.appendChild(cellRole);

    tBody.appendChild(row);
}

function newTabClick() {
    document.getElementById("newTab").classList.add("active");
    document.getElementById("usersTab").classList.remove("active");
    document.getElementById("table").style.display = "none";
    document.getElementById("captionAll").style.display = "none";
    document.getElementById("captionNew").style.display = "";
}

function usersTabClick() {
    document.getElementById("newTab").classList.remove("active");
    document.getElementById("usersTab").classList.add("active");
    document.getElementById("table").style.display = "";
    document.getElementById("captionAll").style.display = "";
    document.getElementById("captionNew").style.display = "none";
}

function userButtonClick() {
    document.getElementById("userButton").classList.add("btn-primary")
    document.getElementById("adminButton").classList.remove("btn-primary")
    document.getElementById("captionAll").style.display = "none";
    document.getElementById("captionOne").style.display = "";
    document.getElementById("navTabs").style.display = "none";
    document.getElementById("table").style.display = "none";
    document.getElementById("adminTitle").style.display = "none";
    document.getElementById("userTitle").style.display = "";
    document.getElementById("userTable").style.display = "";
}

function adminButtonClick() {
    document.getElementById("userButton").classList.remove("btn-primary")
    document.getElementById("adminButton").classList.add("btn-primary")
    document.getElementById("captionAll").style.display = "";
    document.getElementById("captionOne").style.display = "none";
    document.getElementById("navTabs").style.display = "";
    document.getElementById("table").style.display = "";
    document.getElementById("adminTitle").style.display = "";
    document.getElementById("userTitle").style.display = "none";
    document.getElementById("userTable").style.display = "none";
}

