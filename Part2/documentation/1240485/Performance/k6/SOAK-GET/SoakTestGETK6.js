import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';

// Test configuration
export let options = {
    stages: [
        { duration: '10s', target: 50 },   // Ramp-up to 50 users over 10 seconds
        { duration: '700s', target: 50 },  // Sustain 50 users for 700 seconds
        { duration: '10s', target: 0 }, 
        ],
    thresholds: {
        'http_req_duration': ['p(99)<3000'], // 99% of response times should be below 3 seconds
    },
};

// Main test function
export default function () {
    const token = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTczMzMzMzc3MywiYXV0aCI6IlJPTEVfQURNSU4gUk9MRV9VU0VSIiwiaWF0IjoxNzMzMjQ3MzczfQ.bEGxzOIPlcLzI_TVcu_F2NNmiD4pwFr1jeCN2Ufqm-yf99CVfjWQudArd3Q5LS7qaqUPrFw0nssK4JLRy0LoBw'; 

    let res = http.get('http://localhost:8080/api/pet-types', {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token, 
        },
    });
    
    // Check that the response status is 200
    check(res, { 'retrieved pet types': (r) => r.status === 200 });
    
    // Sleep for 1 second to simulate user think time
    sleep(1);
}

// Generate HTML report after test completion
export function handleSummary(data) {
    return {
        "C:\\Users\\user\\Desktop\\SoakGETK6.html": htmlReport(data),
    };
}