window.onload = () => {
  let elements = document.getElementsByTagName('a')
  let sections = document.getElementsByTagName('section')

  const displayChanger = (e) => {
    e.preventDefault()
    removeEverything()
    removeActive()
    e.target.classList.add('active')
    displaySection(e.target.id)
  }

  for (let i = 0; i < elements.length; i++) {
    if (elements[i].classList.contains('nav-link')) {
      elements[i].addEventListener('click', displayChanger)
    }
  }

  const removeActive = () => {
    const elements = document.getElementsByTagName('a')

    for (let i = 0; i < elements.length; i++) {
      elements[i].classList.remove('active')
    }
  }

  const removeEverything = () => {
    for (let i = 0; i < sections.length; i++) {
      if (sections[i].classList.contains('toggle-content')) {
        sections[i].style['display'] = 'none'
      }
    }
  }

  const displaySection = (id) => {
    for (let i = 0; i < sections.length; i++) {
      if (
        sections[i].classList.contains('toggle-content') &&
        sections[i].id == id
      )
        sections[i].style['display'] = 'block'
    }
  }
}
