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
        "C:\\Users\\user\\Desktop\\TestesFR\\k6\\GET\\SOAK\\SoakGETK6.html": htmlReport(data),
    };
}