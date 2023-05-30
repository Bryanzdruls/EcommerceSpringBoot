
const mp = new MercadoPago('TEST-eb73a04e-faad-4b08-9b71-84e2fc98da35');
const bricksBuilder = mp.bricks();

          
mp.bricks().create("wallet", "wallet_container", {
    initialization: {
        preferenceId: "<PREFERENCE_ID>",
    },
 });