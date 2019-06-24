// Paths
export const API='//localhost:8131/';

// export const API_USER='//localhost:8152/';
export const API_USER = '//localhost:8762/users/'; // zuul

export const AGENT_API= API + 'agent/';
export const USER_API= API + 'user/';
export const ADMIN_API= API + 'admin/';
export const EXTRAS_API= API + 'extras/';
export const TYPES_API= API + 'types/';
export const CATEGORIES_API= API + 'cat/';

export const CLOUD_API = 'http://localhost:8010/rating/us-central1/';

export const CLOUD_NOT_APPROVED = 'http://localhost:8010/rating-module/us-central1/getRatingsNotApproved/';
export const CLOUD_APPROVAL = 'http://localhost:8010/rating-module/us-central1/approve/';
export const CLOUD_AVERAGE = 'http://localhost:8010/rating-module/us-central1/averageGrade/';

export const LOGIN_API = 'http://localhost:8762/reservations/';

// Roles
export const ROLE_USER = "ROLE_USER";
export const ROLE_SYS = "ROLE_ADMIN";
export const ROLE_A = "ROLE_AGENT";
