import http from 'k6/http';
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';

// Test configuration
export let options = {
    stages: [
        { duration: '10s', target: 200 },   // Ramp-up to 200 users over 10 seconds
        { duration: '90s', target: 50 },  // Sustain 200 users for 90 seconds
        { duration: '10s', target: 0 }, 
        ],
    thresholds: {
        'http_req_duration': ['p(95)<5000'], // 95% of response times should be below 5 seconds
    },
};


// Load data from CSV
const csvData = new SharedArray("Pet Types", function() {
    return papaparse.parse(open('C:/Users/user/Desktop/TestesFR/k6/POST/Pet_Types.csv'), { header: true }).data;
});

// Main test function
export default function () {
    // Select a random pet type from the CSV data
    const pettype = csvData[Math.floor(Math.random() * csvData.length)];
    
    const petTypeName = (pettype && pettype.petTypeName && pettype.petTypeName.trim() !== "") 
    ? pettype.petTypeName.trim() 
    : "teste";  // Valor padrão para petTypeName

    let payload = JSON.stringify({
    name: petTypeName  // Usar petTypeName com o valor padrão, se necessário
    });

    let url = 'http://localhost:9966/petclinic/api/pettypes';
    let res = http.post(url, payload, { headers: { 'Content-Type': 'application/json' } });

    // Check that the response status is 201 (Created)
    check(res, { 'Created pet type': (r) => r.status === 201 });
 
    // Sleep for 1 second to simulate user think time
    sleep(1);
}

// Generate HTML report after test completion
export function handleSummary(data) {
    return {
        "C:\\Users\\user\\Desktop\\TestesFR\\k6\\POST\\STRESS\\StressPOSTK6.html": htmlReport(data),
    };
}
