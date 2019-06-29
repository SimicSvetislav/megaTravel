export const ZUUL = 'http://localhost:8762/';

//export const API_USER='//localhost:8152/';
export const API_USER = ZUUL + 'users/'; // zuul

//export const API_RESERVATION='//localhost:8122/';
export const API_RESERVATION = ZUUL + 'reservations/'; // zuul

//export const MAIN_API='//localhost:8111/';
export const API_MAIN = ZUUL + 'main/'; // zuul

export const EXTRAS_API= API_MAIN + 'extras/';
export const TYPES_API= API_MAIN + 'types/';
export const CATEGORIES_API= API_MAIN + 'cat/';

//export const API_SEARCH = '//localhost:8123/';
export const API_SEARCH = ZUUL + 'search/';

export const CHAT_API = 'http://localhost:7070/';

export const CLOUD_API = 'http://localhost:8010/rating-module/us-central1/';

// Roles
export const ROLE_USER = "ROLE_USER";
export const ROLE_SYS = "ROLE_ADMIN"; 
export const ROLE_A = "ROLE_AGENT";
