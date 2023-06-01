// Muestra un modal con un mensaje y tipo específico//
function mostrarModal(mensaje, tipo) {
  Swal.fire({
    position: 'top-end',
    icon: tipo,
    title: mensaje,
    showConfirmButton: false,
    timer: 1500
  });
}

// Formatea los precios eliminando ".0" si no es ".01"
function formatPrice() {
  const prices = document.querySelectorAll(".product-price");

  for (const price of prices) {
    if (price.innerText.includes(".0") && !price.innerText.includes(".01")) {
      price.innerText = price.innerText.replace(".0", "");
    }
  }
}

// Redirecciona a la página del producto según su ID
function redirectByProductId() {
  const productLinks = document.querySelectorAll(".grid-item");

  for (const productLink of productLinks) {
    productLink.addEventListener('click', function(event) {
      // Verificar si se hizo clic en el botón "Añadir al carrito"
      if (event.target.classList.contains("product-button")) {
        return; // Salir de la función sin realizar la redirección
      }

      event.preventDefault();
      const productId = this.getAttribute('data-id');
      window.location.href = '/products/' + productId;
    });
  }
}

// Desactiva el arrastrar y soltar en el documento
function disableDragAndDrop() {
  document.body.addEventListener('dragstart', function(event) {
    event.preventDefault();
  });

  document.body.addEventListener('drop', function(event) {
    event.preventDefault();
  });
}

// Agrega un producto al carrito y muestra un mensaje modal
function agregarAlCarrito(productId) {
  const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  
  if (!carrito.includes(productId)) {
    carrito.push(productId);
    mostrarModal("¡Producto agregado al carrito!", "success");
  }
  
  localStorage.setItem("carrito", JSON.stringify(carrito));
  actualizarCantidadCarrito();
  obtenerProductos(); // Actualizar la lista de productos cada vez que se agrega uno al carrito
}

// Actualiza la cantidad de productos en el carrito
function actualizarCantidadCarrito() {
  const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  const cantidad = carrito.length;

  const carritoElement = document.querySelector(".fa-shopping-cart");
  let cantidadElement = carritoElement.querySelector(".cantidad-carrito");

  if (!cantidadElement) {
    cantidadElement = document.createElement("span");
    cantidadElement.classList.add("cantidad-carrito");
    carritoElement.appendChild(cantidadElement);
  }

  cantidadElement.innerText = cantidad;
}

// Evento que se ejecuta al cargar la página
window.addEventListener("load", async function() {
  formatPrice();
  disableDragAndDrop();
  await renderCategories();

  const addButtons = document.getElementsByClassName("product-button");
  for (const addButton of addButtons) {
    addButton.addEventListener("click", function(event) {
      event.preventDefault();
      const productId = this.parentNode.getAttribute("data-id");
      agregarAlCarrito(productId);
    });
  }

  redirectByProductId();
  actualizarCantidadCarrito();

  const tablaProductos = document.getElementById('tablaProductos');
  const inputBusqueda = document.getElementById('inputBusqueda');
  let clickedOnTable = false;

  // Escucha el evento de entrada en el campo de búsqueda
  inputBusqueda.addEventListener('input', function() {
    if (this.value === '') {
      tablaProductos.innerHTML = '';
    } else {
      buscarProductos(this.value);
    }
  });

  // Escucha el evento de clic en la tabla de productos
  tablaProductos.addEventListener('click', function(event) {
    clickedOnTable = true;
    let target = event.target;
    while (target !== tablaProductos) {
      if (target.tagName === 'TR') {
        const productId = target.getAttribute('data-id');
        window.location.href = '/products/' + productId;
        break;
      }
      target = target.parentNode;
    }
  });

  // Escucha el evento de pérdida de foco en el campo de búsqueda
  inputBusqueda.addEventListener('blur', function() {
    setTimeout(function() {
      if (!clickedOnTable) {
        inputBusqueda.value = '';
        tablaProductos.innerHTML = '';
      }
      clickedOnTable = false;
    }, 200);
  });

  // Busca productos según el texto de búsqueda
  async function buscarProductos(textoBusqueda) {
    try {
      const response = await fetch('/search?nombre=' + encodeURIComponent(textoBusqueda));
      const productos = await response.json();

      tablaProductos.innerHTML = '';

      for (const producto of productos) {
        const fila = tablaProductos.insertRow();
        fila.classList.add('generated-row');

        const imagenCell = fila.insertCell();
        const imagen = document.createElement('img');
        imagen.src = producto.imagen;
        imagen.alt = 'Imagen de producto';
        imagen.loading = 'lazy';
        imagen.classList.add('product-thumbnail');
        imagenCell.appendChild(imagen);

        const nombreCell = fila.insertCell();
        nombreCell.textContent = producto.nombre;

        const descripcionCell = fila.insertCell();
        descripcionCell.textContent = producto.descripcion;

        fila.setAttribute('data-id', producto.id); // Agregar la propiedad data-id al elemento tr
      }
    } catch (error) {
      console.error(error);
    }
  }
  
  
  document.querySelector("#categoriasDropdown").addEventListener('click', function (){
	  
	document.querySelector(".container-fluid").style.display = "block";
	
	  
  });
  
  document.querySelector(".container-fluid").addEventListener('click', function (){
	  
	document.querySelector(".container-fluid").style.display = "none";
	  
  });
  
  // Verificar si el carrito se eliminó del localStorage y vaciar el array de productos
  if (!localStorage.getItem("carrito")) {
    localStorage.setItem("carrito", JSON.stringify([]));
  }
  
  obtenerProductos(); // Obtener y mostrar los productos al cargar la página
});

// Obtener los productos del localStorage
const productosLocalStorage = JSON.parse(localStorage.getItem('carrito'));

// Peticion asincrona a la db para obtener un producto del carrito
async function obtenerProductoenDB(productoId) {
  try {
    const response = await fetch(`/productRest/${productoId}`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error al obtener el producto desde la base de datos:', error);
    throw error;
  }
}

// Recorremos el localStorage para obtener todos los productos almacenados
async function obtenerProductos() {
  try {
    const productos = [];
 
    for (const productoId of productosLocalStorage) {
      const producto = await obtenerProductoenDB(productoId);
      productos.push(producto);
    }

    console.log(productos);
    
  } catch (error) {
    console.error('Error al obtener los productos:', error);
  }
}

obtenerProductos();

// Resto del código...



async function getAllCategories() {
	
	const url = await fetch(`/categories`);
	
	const categories = await url.json();	
	
	console.log(categories)
	
	return categories;
	
	
}

async function renderCategories() {
	 	 
	 
  try {
	  
    const categoriesList = document.getElementById("menu"); // Obtener el elemento <ul> con el id "menu"
    
    // Obtener todas las categorías utilizando la función getAllCategories()
    const categories = await getAllCategories();
    
    // Recorrer todas las categorías y crear los elementos <li> correspondientes
    categories.forEach(category => {
		
      const li = document.createElement("li");
      const a = document.createElement("a");
      
      // Establecer los atributos y contenido de los elementos <a> y <li>
      a.href = `/categories/${category.nombre}`;
      
      a.className = "nav-link px-0 text-white font-weight-bold";
      a.textContent = category.nombre; // Utilizar el nombre de la categoría
      
      li.appendChild(a);
      categoriesList.appendChild(li);
    });
    
  } catch (error) {
	  
    console.error("Error al obtener las categorías:", error);
  }
}

	 
 









