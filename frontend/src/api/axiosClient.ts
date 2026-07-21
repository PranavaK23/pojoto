import axios from "axios";

const rawBaseUrl = import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080";
const baseURL = rawBaseUrl.endsWith("/api/v1") ? rawBaseUrl : `${rawBaseUrl.replace(/\/$/, "")}/api/v1`;

const axiosClient = axios.create({
  baseURL,
  timeout: 60000,
});

export default axiosClient;
