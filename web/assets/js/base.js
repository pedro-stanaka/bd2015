/**
 * Check whether an object is empty or not (wider checking than obj === undefined).
 *
 * Warning: for numbers, if the someObj is zero it is empty
 *
 * Depends on: jQuery.trim
 *
 * @param someObj The object to be checked
 * @returns {boolean} True if empty, false otherwise
 */
function isEmpty(someObj){
    if(someObj === undefined || someObj === null){
        return true;
    }else if(typeof someObj === "string" && $.trim(someObj) === ""){
        return true;
    }else return !!(typeof someObj === "number" && someObj === 0);
}

/**
 * Returns a formatted url with the proper base prepended
 * @param {string} url The url to be appended (must begin with '/' )
 * @returns {string}
 */
$.url = function (url) {
    var baseUrl = $('base').attr('href');
    if (isEmpty(baseUrl)) { // application deployed on ROOT
        return window.location.protocol + "//" + window.location.host + url;
    } else { // other path, must prepend the base url
        return baseUrl + url.substr(1);
    }
};