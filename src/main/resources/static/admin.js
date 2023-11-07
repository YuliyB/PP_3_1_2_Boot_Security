const url = 'http://localhost:8080/api/users/';

function getAllUsers() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            loadTable(data)
        })
}

function loadTable(listAllUsers) {
    let res = ``;
    for (let user of listAllUsers) {
        res +=
            `<tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
           <td>${user.roles.map(r => r.name).join(' ')}</td>
                <td>
                    <button class="btn btn-info" type="button"
                    data-toggle="modal" data-target="#editModal"
                    onclick="editModal(${user.id})">Edit</button></td>
                <td>
                    <button class="btn btn-danger" type="button"
                    data-toggle="modal" data-target="#deleteModal"
                    onclick="deleteModal(${user.id})">Delete</button></td>
            </tr>`
    }
    document.getElementById('allUsers').innerHTML = res;
}

function editModal(id) {
    let editId = url + id;
    fetch(editId, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('editId').value = user.id;
            document.getElementById('editUsername').value = user.username;
            document.getElementById('editAge').value = user.age;
            document.getElementById('editEmail').value = user.email;
            document.getElementById('editPassword').value = '';
        })
    });

}

async function editUser() {
    let idValue = document.getElementById('editId').value;
    let usernameValue = document.getElementById('editUsername').value;
    let ageValue = document.getElementById('editAge').value;
    let emailValue = document.getElementById('editEmail').value;
    let passwordValue = document.getElementById('editPassword').value;
    let role = document.getElementById('editRole')
    let listOfRole = []
    for (let i = 0; i < role.options.length; i++) {
        if (role.options[i].selected) {
            listOfRole.push({id: role.options[i].value, name: role.options[i].innerHTML})
        }
    }
    let user = {
        id: idValue,
        username: usernameValue,
        age: ageValue,
        email: emailValue,
        password: passwordValue,
        roles: listOfRole
    }
    await fetch(url, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(user)
    });
    getAllUsers()
}

function deleteModal(id) {
    let delId = url + id;
    fetch(delId, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('deleteId').value = user.id;
            document.getElementById('deleteUsername').value = user.username;
            document.getElementById('deleteAge').value = user.age;
            document.getElementById('deleteEmail').value = user.email;
        })
    });
}

async function deleteUser() {
    const id = document.getElementById('deleteId').value
    let urlDel = url + id;

    let method = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }
    fetch(urlDel, method).then(() => {
        getAllUsers()
    })

}

function newUserTab() {
    document.getElementById('newUserForm').addEventListener('submit', (e) => {
        e.preventDefault()
        let role = document.getElementById('rolesNew')
        let rolesAddUser = []
        for (let i = 0; i < role.options.length; i++) {
            if (role.options[i].selected) {
                rolesAddUser.push({id: role.options[i].value, name: role.options[i].innerHTML})
            }
        }
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                username: document.getElementById('usernameNew').value,
                age: document.getElementById('ageNew').value,
                email: document.getElementById('emailNew').value,
                password: document.getElementById('passwordNew').value,
                roles: rolesAddUser
            })
        })
            .then((response) => {
                if (response.ok) {
                    document.getElementById('usernameNew').value = '';
                    document.getElementById('ageNew').value = '';
                    document.getElementById('emailNew').value = '';
                    document.getElementById('passwordNew').value = '';
                    document.getElementById('nav-home-tab').click()
                    getAllUsers();
                }
            })
    })
}

newUserTab();
getAllUsers();
