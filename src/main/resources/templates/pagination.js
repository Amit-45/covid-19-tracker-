// // Variables to keep track of pagination
// let currentPage = 1;
// const rowsPerPage = 10; // Number of rows to display per page
//
// // Function to update the table with the current page's data
// function updateTable() {
//     const tableBody = document.getElementById('country-table-body');
//     tableBody.innerHTML = '';
//
//     const startIndex = (currentPage - 1) * rowsPerPage;
//     const endIndex = startIndex + rowsPerPage;
//     const paginatedData = locationStats.slice(startIndex, endIndex);
//
//     paginatedData.forEach(locationStat => {
//         const row = document.createElement('tr');
//         // Create and append table cells with locationStat data
//         tableBody.appendChild(row);
//     });
// }
//
// // Function to generate and update pagination links
// function updatePagination() {
//     const pagination = document.getElementById('pagination');
//     pagination.innerHTML = '';
//
//     const totalPages = Math.ceil(locationStats.length / rowsPerPage);
//
//     const previousPage = document.createElement('li');
//     // Add previous page link
//     // ...
//
//     pagination.appendChild(previousPage);
//
//     for (let i = 1; i <= totalPages; i++) {
//         const pageItem = document.createElement('li');
//         // Add page number link
//         // ...
//         pagination.appendChild(pageItem);
//     }
//
//     const nextPage = document.createElement('li');
//     // Add next page link
//     // ...
//
//     pagination.appendChild(nextPage);
// }
//
// // Event listeners for previous and next page links
// document.getElementById('previous-page').addEventListener('click', () => {
//     if (currentPage > 1) {
//         currentPage--;
//         updateTable();
//         updatePagination();
//     }
// });
//
// document.getElementById('next-page').addEventListener('click', () => {
//     const totalPages = Math.ceil(locationStats.length / rowsPerPage);
//     if (currentPage < totalPages) {
//         currentPage++;
//         updateTable();
//         updatePagination();
//     }
// });
//
// // Initial table and pagination update
// updateTable();
// updatePagination();
