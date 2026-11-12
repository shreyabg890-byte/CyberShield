
// Highlight the navigation link for the section currently in view

const sections = document.querySelectorAll("section[id]");
const navLinks = document.querySelectorAll(".nav-links a");

window.addEventListener("scroll", () => {
    let currentSection = "home";

    sections.forEach(section => {
        const sectionTop = section.offsetTop - 150;

        if (window.scrollY >= sectionTop) {
            currentSection = section.getAttribute("id");
        }
    });

    navLinks.forEach(link => {
        link.classList.remove("active");

        if (link.getAttribute("href") === "#" + currentSection) {
            link.classList.add("active");
        }
    });
});
