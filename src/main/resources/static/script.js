
function rowClicked(id, username, name, age, enabled, roles) {
    document.getElementById("id").value = id;
    document.getElementById("username").value = username;
    document.getElementById("name").value = name;
    document.getElementById("age").value = age;
    document.getElementById("enabled").checked = enabled;
    document.getElementById("user").checked = false;
    document.getElementById("admin").checked = false;
    roles.forEach(function(role){
        if (role.authority === "ROLE_ADMIN") {
            document.getElementById("admin").checked = true;
        }
        if (role.authority === "ROLE_USER") {
            document.getElementById("user").checked = true;
        }
    });
}