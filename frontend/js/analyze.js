
const params = new URLSearchParams(window.location.search);
const type = params.get("type");

const pageTitle = document.getElementById("pageTitle");
const pageDescription = document.getElementById("pageDescription");
const inputContainer = document.getElementById("inputContainer");
const analyzeButton = document.getElementById("analyzeButton");
const message = document.getElementById("message");

let userInput;

const services = {
    message: {
        title: "Message Detection",
        description: "Paste your suspicious message below.",
        label: "Enter Your Message",
        placeholder: "Paste your message here..."
    },

    email: {
        title: "Email Detection",
        description: "Paste the email content you want to check.",
        label: "Enter Email Content",
        placeholder: "Paste the email subject and message here..."
    },

    url: {
        title: "URL Detection",
        description: "Enter the website URL you want to check.",
        label: "Enter Website URL",
        placeholder: "https://example.com"
    },

    qr: {
        title: "QR Code Detection",
        description: "Upload a QR code image to check its destination.",
        label: "Upload QR Code Image"
    },

    screenshot: {
        title: "Screenshot Analysis",
        description: "Upload a screenshot to check for possible scams.",
        label: "Upload Screenshot"
    }
};

const selectedService = services[type];

if (!selectedService) {
    pageTitle.textContent = "Invalid Service";
    pageDescription.textContent = "Please select a service from the CyberShield home page.";
    analyzeButton.disabled = true;
} else {

    pageTitle.textContent = selectedService.title;
    pageDescription.textContent = selectedService.description;

    const label = document.createElement("label");
    label.textContent = selectedService.label;
    inputContainer.appendChild(label);

    if (type === "message" || type === "email") {

        userInput = document.createElement("textarea");
        userInput.placeholder = selectedService.placeholder;
        userInput.id = "userInput";

    } else if (type === "url") {

        userInput = document.createElement("input");
        userInput.type = "url";
        userInput.placeholder = selectedService.placeholder;
        userInput.id = "userInput";

    } else {

        userInput = document.createElement("input");
        userInput.type = "file";
        userInput.accept = "image/*";
        userInput.id = "userInput";

    }

    inputContainer.appendChild(userInput);
}

analyzeButton.addEventListener("click", function () {

    message.textContent = "";

    if (!selectedService) return;

    if (type === "qr" || type === "screenshot") {

        if (!userInput.files || userInput.files.length === 0) {
            message.textContent = "Please upload an image first.";
            return;
        }

    } else {

        if (!userInput.value.trim()) {
            message.textContent = "Please enter your content first.";
            userInput.focus();
            return;
        }

        if (type === "url" && !userInput.validity.valid) {
            message.textContent = "Please enter a valid URL.";
            userInput.focus();
            return;
        }
    }

    message.textContent =
        "Your input is ready. Connect the analysis backend to detect threats.";
});
