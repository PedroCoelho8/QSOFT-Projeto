import http from 'k6/http';
import { check, sleep } from 'k6';
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

// Main test function
export default function () {
    let res = http.get('http://localhost:9966/petclinic/api/pettypes', {
        headers: { 'Content-Type': 'application/json' },
    });
    
    // Check that the response status is 200
    check(res, { 'retrieved pet types': (r) => r.status === 200 });
    
    // Sleep for 1 second to simulate user think time
    sleep(1);
}

// Generate HTML report after test completion
export function handleSummary(data) {
    return {
        "C:\\Users\\user\\Desktop\\TestesFR\\k6\\GET\\STRESS\\StressPOSTK6.html": htmlReport(data),
    };
}