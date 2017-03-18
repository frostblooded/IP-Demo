var urlParams;

// Put the query params in the variable urlParams when the page loads
(window.onpopstate = function () {
    var match,
        pl     = /\+/g,  // Regex for replacing addition symbol with a space
        search = /([^&=]+)=?([^&]*)/g,
        decode = function (s) { return decodeURIComponent(s.replace(pl, " ")); },
        query  = window.location.search.substring(1);

    urlParams = {};
    while (match = search.exec(query))
        urlParams[decode(match[1])] = decode(match[2]);
})();

const ACCESS_TOKEN_URI = "https://www.googleapis.com/oauth2/v4/token";
const ACCESS_TOKEN_REDIRECT_URI = "http://localhost:8080/oauth_demo/access_token.html";
const USER_DATA_URI = "https://www.googleapis.com/oauth2/v1/userinfo";
const REDIRECT_WITH_DATA_URL = "http://localhost:8080/oauth_demo/api/oauth/google/receive_email";
const CLIENT_ID = "880159598819-t6qjmm204p3u82ugq7mfr3m8rp4l4p9f.apps.googleusercontent.com";
const CLIENT_SECRET = "iGqywK4AXygoo4vtVU-WyAya";

function logToPage(string) {
    $('body').append('<p>' + string + '</p>');
}

function redirectWithData(data, access_token) {
    $.redirect(REDIRECT_WITH_DATA_URL, {
        access_token: access_token,
        email: data.email,
        name: data.name
    })
}

function getUserData(access_token) {
    logToPage('Getting user info...');

    $.ajax({
        method: 'GET',
        url: USER_DATA_URI + "?access_token=" + access_token,
        success: function (res) {
            console.log(res);
            console.log('User with email ' + res.email + ' found!');
            logToPage('User info received. Redirecting...');
            redirectWithData(res, access_token);
        }
    })
}

$(function () {
    logToPage('Getting access token...');

    var url = ACCESS_TOKEN_URI +
        "?client_id=" + CLIENT_ID +
        "&client_secret=" + CLIENT_SECRET +
        "&code=" + urlParams["code"] +
        "&redirect_uri=" + ACCESS_TOKEN_REDIRECT_URI +
        "&grant_type=authorization_code";

    $.ajax({
        method: 'POST',
        url: url,
        success: function(res) {
            console.log(res);
            console.log("Access token: " + res.access_token);
            logToPage('Access token received');
            getUserData(res.access_token);
        }
    });
});