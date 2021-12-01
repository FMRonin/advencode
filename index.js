const { AocClient } = require('advent-of-code-client');

const client = new AocClient({
    year: 2020,
    day: 1,
    token: '53616c7465645f5fbc9be3e190f84a4f307c611351f8de673c78d93168f9744e957d6647bb6fd7150ca9d59773fab250'
});

const input = client.getInput().then((result) => {
    console.log(result);
}).catch((err) => {
    console.log(err);
});