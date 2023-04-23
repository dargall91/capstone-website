This project is a website designed for Alert Originators to login and be able to view past Wireless Emergency Alerts. The Alert Originator will be able to view alerts via pages of 9 alerts, denoted by their different types, eg: Alert, Update, etc... A filter is available for them to quickly help in locating a specific alert. The alert cards contain a map of the location, the date it was sent out, and the message number. When clicked, a modal appears which shows further information about users recieving the alert.

This project was bootstrapped with [Vite](https://github.com/vitejs/vite).

Node version 16+ is required for the application to run.

(If a previous version of the application was run with create react app, please delete your node_modules folder and do a fresh npm install)

## Important

Please see the 'Database Setup' and 'Starting the Server' sections in wea/README.md before continuing

## Available Scripts

In the project directory, you can run:

### `npm install` -this will install the node modules then you can do npm start to run application

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:8080](http://localhost:8080) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://vitejs.dev/guide/build.html#building-for-production) for more information.

## General Instructions

Before running the website, be sure to have the server and android application launched, and get a message from the android application to populate the database. Otherwise you will end up with no viewable data.

To view the running project, start the server in the /wea folder, and in this folder, run npm start.

When prompted to login, paste the user w-nws.webmaster@noaa.gov to gain access to the alerts being tracked from the National Weather Service.

## Learn More

You can learn more in the [Vite documentation](https://vitejs.dev/guide/).

To learn React, check out the [React documentation](https://reactjs.org/).

### Deployment

This section has moved here: [https://vitejs.dev/guide/static-deploy.html#deploying-a-static-site](https://vitejs.dev/guide/static-deploy.html#deploying-a-static-site)
