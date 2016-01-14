/*
 * @author TIONO KEVIN
 */

// Declaration des variables

        var reste=30
	var interval=null
	// Chargement des images representant les chiffre
	Chiffres = new Array(10)	
        for (i=0;i<10;i++) {
		Chiffres[i] = new Image()
		Chiffres[i].src = 'Ressources/images/' + i + '.gif'
	}
	
	// Definition de la fonction appelé chaque seconde
	function Ecoule() {
		// On modifie l'affichage
		window.document.dizaine.src = Chiffres[Math.floor(reste/10)].src
		window.document.unite.src = Chiffres[reste%10].src
		if (reste == 0) {
			// Une fois les 59 secondes coulees, on le recharge.
			clearInterval(interval)
			alert('Trop tard !!!')
			interval=null
			reste=30
		}
		reste--
	}
