async function login(event) {
    event.preventDefault(); // stop form refresh

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    const user = {
        username: username,
        password: password
    };

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            alert("Login failed");
            return;
        }

        const data = await response.json();

        // Save token
        localStorage.setItem("token", data.jwt);

        alert("Login Successfully!");

        window.location.href = "student.html";

    } catch (error) {
        console.error("Error:", error);
        alert("Server error");
    }
}

// Register User
async function register(event) {
    event.preventDefault(); // stop form refresh

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    const user = {
        username: username,
        password: password
    };

    try {
        const response = await fetch("http://localhost:8080/api/auth/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            alert("Register failed ..!");
            return;
        }
        alert("Register Successfully!");

        window.location.href = "login.html";

    } catch (error) {
        console.error("Error:", error);
        alert("Server error");
    }
}

//Create student data
document.getElementById("createForm").addEventListener("submit", async function (event) {
    event.preventDefault(); // prevent page reload

    const token = localStorage.getItem("token");

    const student = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    try {
        const response = await fetch("http://localhost:8080/api/students", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(student)
        });

        if (!response.ok) {
            alert("Student creation failed");
            return;
        }

        alert("Student created successfully!");

        // Clear form
        document.getElementById("createForm").reset();

        // Redirect to student list page
        window.location.href = "student.html";

    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong!");
    }
});



//fetch All students
async function loadStudents() {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Token not found. Please login first.");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/students", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            alert("Unauthorized or Token expired!");
            return;
        }

        const students = await response.json();
        let list = document.getElementById("studentList");

        list.innerHTML = "";

        students.forEach(student => {
            let li = document.createElement("li");
            li.innerHTML = `${student.id} - ${student.name} - ${student.email}
            <button onclick="updateStudent(${student.id})">Update</button>
            <button onclick="deleteStudent(${student.id})">Delete</button>
            `;
            list.appendChild(li);
        });

    } catch (error) {
        console.error("Error:", error);
        alert("Server not responding");
    }
}

//delete data
async function deleteStudent(id) {
    const token = localStorage.getItem("token");

    if (!confirm("Are you sure you want to delete this student?")) {
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/students/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            alert("Failed to delete student");
            return;
        }

        alert("Student deleted successfully");
        loadStudents(); // refresh list

    } catch (error) {
        console.error("Error:", error);
        alert("Server error");
    }
}


//Update student
function updateStudent(id) {
    window.location.href = `update.html?id=${id}`;
}

async function loadStudentById() {
    const token = localStorage.getItem("token");
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    try {
        const response = await fetch(`http://localhost:8080/api/students/${id}`, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            alert("Failed to load student");
            return;
        }

        const student = await response.json();

        // ✅ Print values in form fields
        document.getElementById("name").value = student.name;
        document.getElementById("email").value = student.email;

    } catch (error) {
        console.error(error);
    }
}

async function updateStudentData() {
    const token = localStorage.getItem("token");
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    const updatedStudent = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    try {
        const response = await fetch(`http://localhost:8080/api/students/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(updatedStudent)
        });

        if (!response.ok) {
            alert("Update failed");
            return;
        }

        alert("Student updated successfully");
        window.location.href = "student.html";

    } catch (error) {
        console.error(error);
    }
}

//logout function
function logout() {
    //remove token from localstorage
    localStorage.removeItem("token");

    window.location.href="login.html";    
}

//Protect to all html file
document.addEventListener("DOMContentLoaded", function () {
    const token = localStorage.getItem("token");

    if (!token) {
        window.location.href = "login.html";
    }
});
