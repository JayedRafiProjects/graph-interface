let shown = false

document.querySelector('#navbar-toggler').addEventListener('click', (e) => {
  e.preventDefault()
  const nav = document.querySelector('#navbar-list')
  if (!shown) nav.style.display = 'block'
  else nav.style.display = 'none'
  shown = !shown
})
