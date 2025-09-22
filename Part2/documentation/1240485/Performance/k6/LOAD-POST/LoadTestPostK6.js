import http from 'k6/http';
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';

// Test configuration
export let options = {
    stages: [
        { duration: '10s', target: 50 },  // Ramp-up to 50 users over 20 seconds
        { duration: '90s', target: 50 },  // Sustain 50 users for 80 seconds
        { duration: '10s', target: 0 }    // Ramp-down to 0 users over 20 seconds
    ],
    thresholds: {
        'http_req_duration': ['p(99)<3000'], // 99% of response times should be below 3 seconds
    },
};

// Load data from CSV
const csvData = new SharedArray("Pet Types", function() {
    return papaparse.parse(open('C:/Users/user/Desktop/Testes/Pet_Types.csv'), { header: true }).data;
});

// Main test function
export default function () {
    const token = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTczMzMzMzc3MywiYXV0aCI6IlJPTEVfQURNSU4gUk9MRV9VU0VSIiwiaWF0IjoxNzMzMjQ3MzczfQ.bEGxzOIPlcLzI_TVcu_F2NNmiD4pwFr1jeCN2Ufqm-yf99CVfjWQudArd3Q5LS7qaqUPrFw0nssK4JLRy0LoBw'; 

    // Select a random pet type from the CSV data
    const pettype = csvData[Math.floor(Math.random() * csvData.length)];
    
    const petTypeName = (pettype && pettype.petTypeName && pettype.petTypeName.trim() !== "") 
    ? pettype.petTypeName.trim() 
    : "teste";  // Valor padrão para petTypeName

    let payload = JSON.stringify({
    name: petTypeName  // Usar petTypeName com o valor padrão, se necessário
    });

    let url = 'http://localhost:8080/api/pet-types';
    let res = http.post(url, payload, { headers: { 'Content-Type': 'application/json', 'Authorization': token } });

    // Check that the response status is 201 (Created)
    check(res, { 'Created pet type': (r) => r.status === 201 });
 
    // Sleep for 1 second to simulate user think time
    sleep(1);
}

// Generate HTML report after test completion
export function handleSummary(data) {
    return {
        "C:\\Users\\user\\Desktop\\LoadPOSTK6.html": htmlReport(data),
    };
}
