const translations = {
    en: {
        "friends-title": "Your Friends",
        "search-title": "Search for Users",
        "emailInput": "Enter email address",
        "search-user": "Search",
        "menu-trips": "TRIPS",
        "menu-plan": "PLAN A TRIP",
        "menu-ranking": "RANKING",
        "menu-friends": "FRIENDS",
        "menu-login": "LOG IN",
        "no-users-found": "No users found with the provided email.",
        "add-friend-btn": "Add to Friends",
        "header.title": "Trip List",
        "header.description": "Browse planned trips",
        "table.header.id": "ID",
        "table.header.start": "Start",
        "table.header.end": "End",
        "table.header.date": "Date",
        "button.theme-switch": "Switch Theme",
    },
    pl: {
        "friends-title": "Twoi znajomi",
        "search-title": "Wyszukiwanie użytkowników",
        "emailInput": "Wpisz adres email",
        "search-user": "Szukaj",
        "menu-trips": "WYCIECZKI",
        "menu-plan": "ZAPLANUJ WYCIECZKĘ",
        "menu-ranking": "RANKING",
        "menu-friends": "ZNAJOMI",
        "menu-login": "ZALOGUJ SIĘ",
        "no-users-found": "Użytkownik o podanym emailu nie został znaleziony.",
        "add-friend-btn": "Dodaj do znajomych",
        "header.title": "Lista Wycieczek",
        "header.description": "Przeglądaj zaplanowane wycieczki",
        "table.header.id": "ID",
        "table.header.start": "Start",
        "table.header.end": "Koniec",
        "table.header.date": "Data",
        "button.theme-switch": "Zmień motyw",
    }
};

function switchLanguage(language) {
    document.querySelectorAll("[data-i18n]").forEach(element => {
        const key = element.getAttribute("data-i18n");
        if (translations[language] && translations[language][key]) {
            element.textContent = translations[language][key];
        }
    });
}

document.getElementById("language-switch").addEventListener("click", () => {
    const currentLang = document.documentElement.lang;
    const newLang = currentLang === "pl" ? "en" : "pl";
    document.documentElement.lang = newLang;
    switchLanguage(newLang);
});