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

async function populateReimbursementsTable() {
    const URL = `http://localhost:8080/users/${localStorage.getItem('user_id')}/reimbursements`;
    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}` //includes JWT into request 
        }
    })


    if (res.status === 200) {
        let Reimbursements = await res.json();
      
            for (let Reimbursement of Reimbursements) {
                let tr = document.createElement('tr');
                

                let td1 = document.createElement('td');
                td1.innerText = Reimbursement.reimb_id;

                let td2 = document.createElement('td');
                td2.innerText = Reimbursement.amount;

                let td3 = document.createElement('td');
                td3.innerText = Reimbursement.description;

                let td4 = document.createElement('td');
                 if(Reimbursement.author == 1) {
                    td4.innerText = "Jurgen Klopp";
                 } else if(Reimbursement.author == 2) {
                     td4.innerText = "James Milner";
                 } else if(Reimbursement.author == 3) {
                     td4.innerText = "Mohamed Salah";
                 } else if(Reimbursement.author == 4) {
                    td4.innerText = "Luis Diaz" 
                 } else td4.innerText = Reimbursement.author;
                 
                let td5 = document.createElement('td');
                if(Reimbursement.resolver == 1) {
                    td5.innerText = "Jurgen Klopp"
                } else {
                    td5.innerText = "Not resolved, see status";
                }

                let td6 = document.createElement('td');
                if(Reimbursement.status_id == 1) {
                    td6.innerText = "Pending";
                } else if(Reimbursement.status_id == 2) {
                    td6.innerText = "Approved";
                } else if(Reimbursement.status_id == 3) {
                    td6.innerText = "Denied";
                } else {
                    td6.innerText = "Status not defined";
                }

                
                let td7 = document.createElement('td');
                if(Reimbursement.type_id == 1) {
                    td7.innerText = "Lodging";
                } else if(Reimbursement.type_id == 2) {
                    td7.innerText = "Travel";
                } else if(Reimbursement.type_id == 3) {
                    td7.innerText = "Food";
                } else if(Reimbursement.type_id == 4) {
                    td7.innerText = "Other";
                } else {
                    td7.innerText = "Type of reimbursement not defined";
                }

                let td8 = document.createElement('td');
                td8.innerText = Reimbursement.username;
                
                let td9 = document.createElement('td');
                td9.innerText = Reimbursement.user_role;

                let td10 = document.createElement('td');
                td10.innerText = Reimbursement.reimb_receipt;
                
                let td11 = document.createElement('td');
                td11.innerText = Reimbursement.user_id; 
                
  
                    
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
                tr.appendChild(td11);

                let tbody = document.querySelector('#Reimbursements-tbl > tbody');
                tbody.appendChild(tr); 
            }
                                           
        }
       
    }