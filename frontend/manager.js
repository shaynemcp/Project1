
//////////// LOGOUT BUTTON////////////
let logoutBtn = document.querySelector('#logout-btn');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('user_role');
    localStorage.removeItem('user_id');
    localStorage.removeItem('jwt');
    window.location =  'index.html';
});

////////// REIMBURSEMENTS TABLE //////////////
window.addEventListener('load', (event) => {
    populateReimbursementsTable();
});

async function populateReimbursementsTable() {
    const URL = `http://localhost:8080/reimbursements`;
    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}` //includes JWT into request 
        }
    });
    


    if (res.status === 200) {
        let Reimbursements = await res.json();
      
            for (let Reimbursement of Reimbursements) {
                let tr = document.createElement('tr');
                

                let td1 = document.createElement('td');
                td1.innerText = Reimbursement.reimb_id;

                let td2 = document.createElement('td');
                td2.innerText = Reimbursement.amount;

                let td3 = document.createElement('td');
                td3.innerText = Reimbursement.submitted;

                let td4 = document.createElement('td');
                td4.innerText = Reimbursement.resolved;

                let td5 = document.createElement('td');
                td5.innerText = Reimbursement.description;

                let td6 = document.createElement('td');
                td6.innerText = Reimbursement.author;

                let td7 = document.createElement('td');
                 if(Reimbursement.status_id == 2){
                    td7.innerText = "Jurgen Klopp";
                } else if (Reimbursement.status_id == 3){
                    td7.innerText = "Jurgen Klopp"
                } else {
                    td7.innerText = "See status"
                }

                let td8 = document.createElement('td');
                if(Reimbursement.status_id == 1) {
                    td8.innerText = "Pending";
                } else if(Reimbursement.status_id == 2) {
                    td8.innerText = "Approved";
                } else if(Reimbursement.status_id == 3) {
                    td8.innerText = "Denied";
                } else {
                    td8.innerText = "Status not defined";
                }

                
                let td9 = document.createElement('td');
                if(Reimbursement.type_id == 1) {
                    td9.innerText = "Lodging";
                } else if(Reimbursement.type_id == 2) {
                    td9.innerText = "Travel";
                } else if(Reimbursement.type_id == 3) {
                    td9.innerText = "Food";
                } else if(Reimbursement.type_id == 4) {
                    td9.innerText = "Other";
                } else {
                    td9.innerText = "Type of reimbursement not defined";
                }

                let td10 = document.createElement('td');
                td10.innerText = Reimbursement.username;
                
                let td11 = document.createElement('td');
                td11.innerText = Reimbursement.user_role;

                let td12 = document.createElement('td');
                td12.innerText = Reimbursement.reimb_receipt;
                
                let td13 = document.createElement('td');
                td13.innerText = Reimbursement.user_id; 
                
  
                    
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
                tr.appendChild(td12);
                tr.appendChild(td13);

                let tbody = document.querySelector('#Reimbursements-tbl > tbody');
                tbody.appendChild(tr); 

                //Deny or Approve Reimbursements   
                if (Reimbursement.status_id == 1) {

                let changeStatus = document.createElement('input');
                changeStatus.setAttribute('type', 'number');
                changeStatus.setAttribute('Approved', '2');
                changeStatus.setAttribute('Denied','3');

                let statusButton = document.createElement('button');
                statusButton.innerText = 'Approve/Deny';

                statusButton.addEventListener('click', async () => {
                    let status = changeStatus.value;

                    try {
                        let res = await fetch(`http://localhost:8080/reimbursements/${Reimbursement.reimb_id}?status_id=${status}`, {
                            method: 'PATCH',
                            headers: {
                                'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                            }
                        });

                        if (res.status === 200) {
                            populateAssignmentsTable(); // Have this function call itself
                        }

                    } catch (e) {
                        console.log(e);
                    }
                    
                });
                tr.appendChild(changeStatus);
                tr.appendChild(statusButton);
            

                //     let deny = document.createElement('button');
                //     deny.innerText = 'Deny';
                //     let approve = document.createElement('button');
                //     approve.innerText = 'Approve'
                //     tr.appendChild(deny);
                //     tr.appendChild(approve);

                //     deny.addEventListener('click', async() => {
                //         console.log('clicked deny button');
                //         // let response = 3 ;


                //         let statusform = document.getElementById('Reimbursement');
                //         let formdata = new FormData(statusform);
                //         formdata.append('user_id', Reimbursement.user_id);
                //         formdata.append('reimb_status_id', Reimbursement.status_id);
                //         // formdata.append('resolver', Reimbursement.user_id);
                //         // formdata.append('username', Reimbursement.username);
                //         let res = await fetch(`http://localhost:8080/reimbursements/${Reimbursement.reimb_id}`, {
                //             method: 'PATCH',
                //             headers: {
                //                 'Authorization': `Bearer ${localStorage.getItem('jwt')}`},
                //             body: formdata  
                //         }) 
                //     if(res.status_id === 200 ) {
                //         populateReimbursementsTable();

                //     }
                // });


                } //Ends if statement, DO NOT LOSE
            }
                                           
        }
       
    }
 
                    
