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
    
    const token = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTczMzMzMzc3MywiYXV0aCI6IlJPTEVfQURNSU4gUk9MRV9VU0VSIiwiaWF0IjoxNzMzMjQ3MzczfQ.bEGxzOIPlcLzI_TVcu_F2NNmiD4pwFr1jeCN2Ufqm-yf99CVfjWQudArd3Q5LS7qaqUPrFw0nssK4JLRy0LoBw'; 

    let res = http.get('http://localhost:8080/api/pet-types', {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token, 
        },
    });
    
    // Verifica se o status da resposta é 200
    check(res, { 'retrieved pet types': (r) => r.status === 200 });
    
    // Simula o tempo de pensamento do usuário
    sleep(1);
}

// Gerar relatório HTML após a execução do teste
export function handleSummary(data) {
    return {
        "C:\\Users\\user\\Desktop\\StressGETK6.html": htmlReport(data),
    };
}
