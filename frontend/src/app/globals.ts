export const API_USER='//localhost:8152/';
//export const API_USER = '//localhost:8762/users/'; // zuul
export const API_RESERVATION='//localhost:8122/';
//export const API_RESERVATION = '//localhost:8762/reservations/'; // zuul
//export const MAIN_API='//localhost:8111/';
export const API_MAIN = '//localhost:8762/main/';
export const EXTRAS_API= API_MAIN + 'extras/';
export const TYPES_API= API_MAIN + 'types/';
export const CATEGORIES_API= API_MAIN + 'cat/';

export const API_SEARCH = '//localhost:8762/search/';

export const CHAT_API = 'http://localhost:7070/';

export const CLOUD_API = 'http://localhost:8010/rating/us-central1/';
export const CLOUD_API2 = 'http://localhost:8010/rating-module/us-central1/';
export const CLOUD_NOT_APPROVED = 'http://localhost:8010/rating-module/us-central1/getRatingsNotApproved/';
export const CLOUD_APPROVAL = 'http://localhost:8010/rating-module/us-central1/approve/';
export const CLOUD_AVERAGE = 'http://localhost:8010/rating-module/us-central1/averageGrade/';

// Roles
export const ROLE_USER = "ROLE_USER";
export const ROLE_SYS = "ROLE_ADMIN"; 
export const ROLE_A = "ROLE_AGENT";
