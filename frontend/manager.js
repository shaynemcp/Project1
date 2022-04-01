
let logoutBtn = document.querySelector('#logout-btn');
logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('user_role');
    // localStorage.removeItem('author');
    localStorage.removeItem('jwt');
    window.location =  'index.html';
});
window.addEventListener('load', (event) => {
    populateReimbursementsTable();
});

async function populateReimbursementsTabl() {
    const URL = `http://localhost:8080/reimbursements`;
    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localstorage.getItem('jwt')}` //includes JWT into request 
        }
    })
    if (res.status === 200) {
        let reimbursements = await res.json();
    //     let tbody = document.querySelector('#reimbursements-tbl > tbody');
    //     tbody.innerHTML = '';  //clears out tbody
    // }
    let tbody = document.querySelector('#Reimbursements-tbl > tbody');
        tbody.innerHTML = '';
        for (let reimbursements of reimbursements) {
            let tr = document.createElement('tr');

            let td1 = document.createElement('td');
            td1.innerText = reimbursements.reimbId;

            let td2 = document.createElement('td');
            td2.innerText = reimbursements.amount;

            let td3 = document.createElement('td');
            td3.innerText = reimbursements.description;

            let td4 = document.createElement('td');
            td4.innerText = reimbursements.author;

            let td5 = document.createElement('td');
            td5.innerText = reimbursements.resolver;

            
            let td7 = document.createElement('td');
            if(reimbursement.reimb_type_id == 1) {
                td7.innerText = "Lodging";
            } else if(reimbursement.reimb_type_id == 2) {
                td7.innerText = "Travel";
            } else if(reimbursement.reimb_type_id == 3) {
                td7.innerText = "Food";
            } else if(reimbursement.reimb_type_id == 4) {
                td7.innerText = "Other";
            } else {
                td7.innerText = "Type of reimbursement not defined";
            }

            let td6 = document.createElement('td');
            if(reimbursement.reimb_status_id == 1) {
                td6.innerText = "Pending";
            } else if(reimbursement.reimb_status_id == 2) {
                td6.innerText = "Approved";
            } else if(reimbursement.reimb_status_id == 3) {
                td6.innerText = "Denied";
            } else {
                td6.innerText = "Status not defined";
            }

            let td8 = document.createElement('td');
            td8.innerText = reimbursements.user_id; 

            let td9 = document.createElement('td');
            td9.innerText = reimbursements.userName;
            
            let td10 = document.createElement('td');
            td10.innerText = reimbursements.userRole;
            
            // let td11 = document.createElement('td');
            // let imgElement = document.createElement('img');
            // imgElement.setAttribute('src', `http://localhost:8080/tickets/${reimbursements.id}/image`);
            // imgElement.style.height = '100px';
            // td11.appendChild(imgElement);       
                
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tr.appendChild(td9);
            tr.appendChild(td10);
            // tr.appendChild(td11);
        }
    }
}

// let getReimBtn = document.querySelector('#getReim-btn');
// getReimbursements.addEventListener('click', async () => {
//     let usernameInput = document.querySelector('#username');
//     let userpassInput = document.querySelector('#userpass');
//     const URL = 'http://localhost:8080/login';
//     const jsonString = JSON.stringify({
//         "username": usernameInput.value,
//         "userpass": userpassInput.value
//     });
// }
