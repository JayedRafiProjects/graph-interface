document.querySelector('#navbar-toggler').addEventListener('click', (e) => {
  e.preventDefault()
  const nav = document.querySelector('#navbar-list')
  nav.classList.toggle('hide')
})
