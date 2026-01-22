Compose-tilanhallinnassa staten eli tilan muuttuessa piirretään uudelleen (recomposition) ne UI-komponentit, jotka käyttävät kyseistä tilaa.

ViewModel on parempi kuin pelkän rememberin käyttö. Konfiguraatiomuutokset, kuten näytön kääntäminen nollaavat rememberin. ViewModel säilyy näissä tilanteissa ja sen käyttö tekee myös koodista selkeämpää ja helpompaa testata.
