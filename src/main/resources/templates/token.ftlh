<div class="modal" id="token-modal">
    <div id="token-modal-box" class="modal__box">

        <button onclick="closeModal()" class="modal__close-btn" id="close-my-modal-btn">
            <i class="fa-solid fa-xmark"></i>
        </button>

        <form id="order-checkout" onsubmit="addToken(event)">

            <input id="accessToken" type="text" placeholder="Input your token from Dropbox Developers" class="form-control">

            <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Submit">
        </form>

    </div>
</div>