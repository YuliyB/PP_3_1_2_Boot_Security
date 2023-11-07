const userUrl = 'http://localhost:8080/api/user';


function getPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformation(user))
}

function getInformation(user) {

    document.getElementById('userTableInfo').innerHTML = `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            
            <td>${user.roles.map(r => r.name).join(' ')}</td>
        </tr>`;

    document.getElementById('basicInfo').innerHTML = `<a class="navbar-brand text-white"><b>${user.username}</b>  with Roles:
        ${user.roles.map(r => r.name).join(' ')}</a>`;
}

getPage();