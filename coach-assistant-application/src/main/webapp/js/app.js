function showToast(id) {
    const toastElement = document.querySelector(`#${id}`);
    const toast = new bootstrap.Toast(toastElement);
    toast.show();
}