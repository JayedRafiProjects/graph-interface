// Line Graph

function drawLine() {
  const labels = ['January', 'February', 'March', 'April', 'May', 'June']

  const data = {
    labels: labels,
    datasets: [
      {
        label: 'Line dataset',
        backgroundColor: 'rgb(255, 99, 132)',
        // backgroundColor corresponds to line color
        borderColor: 'rgb(255, 99, 132)',
        data: [0, 10, 5, 2, 20, 30, 45],
        // data: dots on the graph
      },
    ],
  }

  const config = {
    type: 'line',
    data: data,
    options: {},
  }

  const myChart = new Chart(document.getElementById('lineChart'), config)
}

function drawBarChart() {
  // Bar Chart
  const labels = ['January', 'February', 'March', 'April', 'May', 'June']

  const data = {
    labels: labels,
    datasets: [
      {
        label: 'Bar Dataset',
        data: [65, 59, 80, 81, 56, 55, 40],
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(255, 159, 64, 0.2)',
          'rgba(255, 205, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(201, 203, 207, 0.2)',
        ],
        borderColor: [
          'rgb(255, 99, 132)',
          'rgb(255, 159, 64)',
          'rgb(255, 205, 86)',
          'rgb(75, 192, 192)',
          'rgb(54, 162, 235)',
          'rgb(153, 102, 255)',
          'rgb(201, 203, 207)',
        ],
        borderWidth: 1,
      },
    ],
  }

  const config = {
    type: 'bar',
    data: data,
    options: {
      scales: {
        y: {
          beginAtZero: true,
        },
      },
    },
  }

  const myChart = new Chart(document.getElementById('barChart'), config)
}

// Pie Chart
function drawPieChart() {
  const data = {
    labels: ['Red', 'Blue', 'Yellow'],
    datasets: [
      {
        label: 'Pie Dataset',
        data: [300, 50, 100],
        backgroundColor: [
          'rgb(255, 99, 160)',
          'rgb(10, 162, 235)',
          'rgb(255, 205, 86)',
        ],
        hoverOffset: 4,
      },
    ],
  }

  const config = {
    type: 'pie',
    data: data,
  }

  const myChart = new Chart(document.getElementById('pieChart'), config)
}

// Calling methods to draw
drawLine()
drawBarChart()
drawPieChart()
