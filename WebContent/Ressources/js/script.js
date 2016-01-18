/*
 * Projet d'intégration 
 * @uthor Enrifath TIDJANI
 * Script de validation des formulaires
 */
$(document).ready(function() {
    $('#inscriptionForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nom: {
                row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le nom est obligatoire'
                    },
			        stringLength: {
			            min: 2,
			            max: 30,
			            message: 'le nom doit être compris entre 2 et 30 caractères'
			        },
                }
            },
            prenom: {
                row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le prénom est obligatoire'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: 'le prénom doit être compris entre 2 et 30 caractères'
                    },
                }
            },
            mail: {
            	row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le champ email ne doit pas être vide'
                    },
                    emailAddress: {
                        message: 'votre email n est pas valide '
                    }
                }
            },
            mdp: {
            	row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le mot de passe est obligatoire'
                    },
                    different: {
                        field: 'nom',
                        message: 'le mot de passe doit être différent du nom'
                    },
                    different: {
                        field: 'prenom',
                        message: 'le mot de passe doit être différent du prenom'
                    },
                    stringLength: {
                        min: 8,
                        max: 30,
                        message: 'votre mot de passe doit contenir au minimum 8 caractères et 30 au maximun'
                    }
                }
            },
            reMdp: {
            	row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le mot de passe est obligatoire'
                    },
                    identical: {
                        field: 'mdp',
                        message: 'les mots de passe doivent être identiques'
                    }
                }
            }
            
        }
    });
});


$(document).ready(function() {
    $('#loginForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            mail: {
                validators: {
                    notEmpty: {
                        message: 'le champ email ne doit pas être vide'
                    },
                    emailAddress: {
                        message: 'votre email n est pas valide '
                    }
                }
            },
            mdp: {
            	row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le mot de passe est obligatoire'
                    },
                    different: {
                        field: 'nom',
                        message: 'le mot de passe doit être différent du nom'
                    },
                    different: {
                        field: 'prenom',
                        message: 'le mot de passe doit être différent du prenom'
                    },
                    stringLength: {
                        min: 8,
                        max: 30,
                        message: 'votre mot de passe doit contenir au minimum 8 caractères et 30 au maximun'
                    }

                }
            }
        }
    });
});