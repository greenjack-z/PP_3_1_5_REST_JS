window.onload = async function () {
    window.users = await fetchUsers();
    window.loggedUser = await fetchLoggedUser();
    console.log(loggedUser)
    window.roles = await fetchRoles();

    document.getElementById("loggedUser").innerHTML = loggedUser.username;
    document.getElementById("loggedUserRoles").innerHTML = loggedUser.roles.map(role => role.authority).map(s => s.replace("ROLE_", " "));

    let select = document.getElementById("new-form").children.namedItem("new-roles");
    fillSelectWithRoles(select, null);

    addUserInTable(loggedUser, document.getElementById("user-table-body"));
    users.forEach((user, index) => {
        addUserInAdminTable(user, index, document.getElementById("admin-table-body"));
    })

    let modalWindow = document.getElementById("modalWindow");
    modalWindow.addEventListener("show.bs.modal", event => {
        let button = event.relatedTarget;
        document.getElementById("modal-caption").innerHTML = button.getAttribute("data-bs-caption");
        let form = document.getElementById("modal-fieldset");
        let userIndex = button.getAttribute("data-bs-index");
        form.disabled = button.getAttribute("data-bs-form-disabled") === "true";
        fillFormWithUserData(users[userIndex], form);
        let okButton = document.getElementById("button-modal-OK");
        okButton.innerHTML = button.getAttribute("data-bs-ok-text");
        okButton.classList = button.classList;
        okButton.value = button.getAttribute("data-bs-user-id");
    })

    modalWindow.addEventListener("submit", event => {
        event.preventDefault();
        deleteUser(event.submitter.value);
        return false;
    })

    let newForm = document.getElementById("new-form");
    newForm.addEventListener("submit", event => {
        event.preventDefault();
        addUser(event.target);
        return false;
    })
}

async function fetchUsers() {
    let response = await(fetch("/api/users"));
    return await response.json();
}

async function fetchLoggedUser() {
    let response = await(fetch("/api/user"));
    return await response.json();
}

async function fetchRoles() {
    let response = await(fetch("/api/roles"));
    return await response.json();
}

function addUserInTable(user, table) {
    let row = table.insertRow();
    row.id = "r" + user.id;
    row.insertCell(0).innerHTML = user.id;
    row.insertCell(1).innerHTML = user.firstname;
    row.insertCell(2).innerHTML = user.lastname;
    row.insertCell(3).innerHTML = user.age;
    row.insertCell(4).innerHTML = user.email;
    row.insertCell(5).innerHTML = user.roles.map(role => role.authority).map(s => s.replace("ROLE_", " "));
}

function createInTableButton(btnClass, btnValue, userIndex, userId, caption, okText, formDisabled) {
    let button = document.createElement("input");
    button.type = "button";
    button.classList.add("btn", btnClass);
    button.value = btnValue;
    button.dataset.bsToggle = "modal";
    button.dataset.bsTarget = "#modalWindow";
    button.dataset.bsIndex = userIndex;
    button.dataset.bsUserId = userId;
    button.dataset.bsCaption = caption;
    button.dataset.bsOkText = okText;
    button.dataset.bsFormDisabled = formDisabled;
    return button;
}

function addUserInAdminTable(user, index, table) {
    addUserInTable(user, table);
    let row = table.rows[table.rows.length - 1];
    row.insertCell(row.cells.length).appendChild(createInTableButton("btn-info", "Edit", index, user.id, "Edit user", "Save changes", "false"));
    row.insertCell(row.cells.length).appendChild(createInTableButton("btn-danger", "Delete", index, user.id, "Delete user", "Delete", "true"));
}

function fillFormWithUserData(user, form) {
    form.children.namedItem("input-id").value = user.id;
    form.children.namedItem("input-firstname").value = user.firstname;
    form.children.namedItem("input-lastname").value = user.lastname;
    form.children.namedItem("input-age").value = user.age;
    form.children.namedItem("input-email").value = user.email;
    form.children.namedItem("input-password").value = user.password;

    let select = form.children.namedItem("select-roles");
    fillSelectWithRoles(select, user);

    form.children.namedItem("checkbox-enabled").checked = user.enabled;
    form.children.namedItem("checkbox-locked").checked = user.locked;
}

function fillSelectWithRoles(select, user) {
    select.size = roles.length;
    select.innerHTML = "";
    roles.forEach(role => {
        let option = document.createElement("option");
        option.innerHTML = role.authority.replace("ROLE_", "");
        if (user != null) {
            option.selected = user.roles.map(r => r.authority).includes(role.authority);
        }
        select.appendChild(option);
    })
}

async function deleteUser(id) {
    let response = await(fetch("/api/" + id, {method: "DELETE"}));
    console.log(response.status);
    if (response.ok) {
        let row = document.getElementById("r" + id);
        row.parentNode.removeChild(row);
        document.getElementById("button-modal-cancel").click();
    }
}

async function addUser(form){
    let formData = new FormData(form);
    let formDataObject = Object.fromEntries(formData);
    let rolesList = formData.getAll("roles").map(name => new Object({authority: ("ROLE_" + name)}));
    formDataObject.roles = rolesList;
    let json = JSON.stringify(formDataObject);

    let response = await(fetch("/api/add", {method: "POST", headers: {"Content-Type": "application/json"},  body: json}));
    if (response.status === 201) {
        let user = await response.json();
        addUserInAdminTable(user, window.users.length, document.getElementById("admin-table-body"));
        users.push(user);
        document.getElementById("users-tab").click();
    }
}




