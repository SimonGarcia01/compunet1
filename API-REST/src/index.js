const express = require("express")
const app = express()
app.use(express.json())

const port = 5000

// método | verbo GET
app.get("/", (req, res) => {
    res.status(200).send("Hola API")
})

app.post("/", (req, res) => {
    // 201 -> OK -> Data Created : https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/201
    res.status(201).send("element created")  
})

app.put("/", (req, res) => {
    res.status(204).send("NO Content in edit")  
})

app.delete("/", (req, res) => {
    res.status(204).send("NO content in delete")  
})


app.listen(port)

