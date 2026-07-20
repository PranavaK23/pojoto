# Deployment Guide

This document outlines the step-by-step configuration required to deploy this full-stack application on free hosting tiers: **Vercel** for the frontend and **Render** for the backend.

---

## 1. Backend Deployment (Render)

We will use Render to host the Spring Boot backend and its PostgreSQL database.

### Step 1.1: Create a PostgreSQL Database on Render
1. Go to the Render Dashboard and click **New +** > **PostgreSQL**.
2. Give your database a name (e.g., `entertainment-hub-db`).
3. Select the free instance type and click **Create Database**.
4. Once created, copy the **Internal Database URL**. You will use this in the next step.

### Step 1.2: Deploy the Web Service
1. Go to the Render Dashboard and click **New +** > **Web Service**.
2. Connect your GitHub repository and select the `entertainment-hub` project.
3. Configure the Web Service with the following exact settings:

| Setting | Value |
| :--- | :--- |
| **Name** | `entertainment-hub-backend` (or your choice) |
| **Root Directory** | `backend` |
| **Environment** | `Docker` |

### Step 1.3: Configure Environment Variables
Scroll down to the **Environment Variables** section and click **Add Environment Variable**. Add the following variables:

| Key | Value | Notes |
| :--- | :--- | :--- |
| `SPRING_PROFILES_ACTIVE` | `prod` | Tells Spring to use `application-prod.yml` |
| `DATABASE_URL` | *Paste your Internal Database URL* | From Step 1.1 (Change `postgres://` to `jdbc:postgresql://` if required by the driver, but Render's URL typically works or requires minor adjustment) |
| `DATABASE_USERNAME` | *Your DB Username* | From Render PostgreSQL dashboard |
| `DATABASE_PASSWORD` | *Your DB Password* | From Render PostgreSQL dashboard |

4. Click **Create Web Service**. Wait for the deployment to finish, and copy your backend live URL (e.g., `https://entertainment-hub-backend.onrender.com`).

---

## 2. Frontend Deployment (Vercel)

We will use Vercel to host the React/Vite frontend.

### Step 2.1: Deploy to Vercel
1. Go to your Vercel Dashboard and click **Add New...** > **Project**.
2. Import your GitHub repository.
3. Configure the Project with the following exact settings:

| Setting | Value |
| :--- | :--- |
| **Project Name** | `entertainment-hub-frontend` (or your choice) |
| **Framework Preset** | `Vite` |
| **Root Directory** | `frontend` |
| **Build Command** | `npm run build` |
| **Output Directory** | `dist` |

### Step 2.2: Configure Environment Variables
Open the **Environment Variables** dropdown on the configuration page and add the following:

| Key | Value | Notes |
| :--- | :--- | :--- |
| `VITE_API_BASE_URL` | `https://your-backend-url.onrender.com/api/v1` | Replace with your actual Render backend URL. **Do not** add a trailing slash. |

4. Click **Deploy**. Vercel will automatically build and deploy your frontend.

---

## Final Verification
Once both deployments are successful:
1. Open your Vercel frontend URL.
2. The frontend will now communicate directly with your Render backend over the internet!
