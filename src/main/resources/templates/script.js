function buttonClick() {
    let response = await fetch('/api/user');
    let user = response.json();
}