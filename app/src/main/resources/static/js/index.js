const IndexService = {
    getToken: () => localStorage.getItem('token'),
    setToken: (token) => localStorage.setItem('token', token),
    getRole: () => localStorage.getItem('role'),
    setRole: (role) => localStorage.setItem('role', role),
    logout: () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        window.location.href = '/login';
    },
    getAuthHeaders: () => ({
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json'
    })
};
