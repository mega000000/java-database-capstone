document.addEventListener('DOMContentLoaded', () => {
    const header = document.getElementById('header-container');
    if (header) {
        header.innerHTML = 
            <header>
                <h2>Smart Clinic Management</h2>
                <button onclick="IndexService.logout()">Logout</button>
            </header>
        ;
    }
});
