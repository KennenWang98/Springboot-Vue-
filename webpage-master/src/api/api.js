import axios from 'axios';

let base = '/new';

export const requestLogin = params => { return axios.post(`/new/admin/login`, params).then(res => res.data); };

export const requestCreateLicense = params => { return axios.get(`/new/code/add`, { params: params }); };
//export const requestCreateLicense = params => { return axios.post(`/new/code/add`, params).then(res => res.data); };
export const requestChangeLicense = params => { return axios.post(`/new/code/change`, params).then(res => res.data); };
export const requestDeleteLicense = params => { return axios.get(`/new/code/delete`, { params: params }); };

export const requestCreateSubject = params => { return axios.post(`/new/subject/add`, params).then(res => res.data); };
export const requestChangeSubject = params => { return axios.post(`/new/subject/change`, params).then(res => res.data); };
export const requestDeleteSubject = params => { return axios.get(`/new/subject/delete`, { params: params }); };

export const requestCreateBook = params => { return axios.post(`/new/book/add`, params).then(res => res.data); };
export const requestChangeBook = params => { return axios.post(`/new/book/change`, params).then(res => res.data); };
export const requestDeleteBook = params => { return axios.get(`/new/book/delete`, { params: params }); };


//export const requestCreateClass = params => { return axios.post(`/new/class/add`, params).then(res => res.data); };
export const requestCreateClass = params => { return axios.get(`/new/class/add`, { params: params }); };
export const requestChangeClass = params => { return axios.post(`/new/class/change`, params).then(res => res.data); };
export const requestDeleteClass = params => { return axios.get(`/new/class/delete`, { params: params }); };
export const requestSearchBook = params => { return axios.get(`/new/class/book`, { params: params }); };
export const requestSearchStudents = params => { return axios.get(`/new/class/students`, { params: params }); };

export const requestCreateSchool = params => { return axios.post(`/new/school/add`, params).then(res => res.data); };
export const requestChangeSchool = params => { return axios.post(`/new/school/change`, params).then(res => res.data); };
export const requestDeleteSchool = params => { return axios.get(`/new/school/delete`, { params: params }); };


export const requestSearchTeacher = params => { return axios.get(`/new/school/teachers`, { params: params }); };
export const requestChangeTeacher = params => { return axios.post(`/new/teacher/change`, params).then(res => res.data); };
export const requestDeleteTeacher = params => { return axios.get(`/new/teacher/delete`, { params: params }); };
