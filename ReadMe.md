# Projet JPA - Internet Movie DataBase

## Description

Les données sont réparties dans 6 fichiers CSV :

1. films.csv : liste des films

2. acteurs.csv : liste des acteurs

3. realisateurs.csv : liste des réalisateurs

4. roles.csv : listes des rôles par films

5. film_realisateurs.csv : réalisateurs par films

6. pays.csv : liste des pays

## Objectifs

Les 4 objectifs de ce projet sont les suivants :

1. Réaliser un document de conception :  
    a. Si vous optez pour UML, vous devez fournir un diagramme de classes et un diagramme entités relations.  
    b. Si vous optez pour la démarche Merise, vous devez fournir un modèle Conceptuel de Données et un Modèle Physique de Données.  

2. Mettre en place une base de données.

3. Mettre au point une application permettant d’insérer les données en base de données.

4. Mettre au point une application avec un menu et permettant d’extraire des données.

## Exigences

**Exigence n°1 : Qualité de code**  

- Renseigner la Javadoc
- Structurer votre code

**Exigence n°2 : Pas de duplication des données en base**

- Les données suivantes doivent être des classes et non des String: Lieu de naissance, pays, langue, genre.
- Les lieux de naissance doivent être uniques
- Les pays doivent être uniques
- Les langues doivent être uniques
- Les genres doivent être uniques
- Les dates doivent être de type LocalDate ou LocalDateTime côté Java. 

## Tâches

**Tâche n°1** : Analyser les fichiers afin d’en comprendre la structure

**Tâche n°2** : Réaliser un document de conception avec les diagrammes demandés. Ce document sera comité dans un répertoire appelé « conception » et situé à la racine du projet.

**Tâche n°3** : Réaliser l’application permettant de parser les fichiers (CSV : facile ou JSON en utilisant la librairie Jackson : difficile) et de mettre en BDD les données (en utilisant JPA).

**Tâche n°4** : Réaliser une seconde application dotée d’un menu et permettant à l’utilisateur de réaliser des recherches dans les données. Voici le menu proposé :

1. Affichage de la filmographie d’un acteur donné

2. Affichage du casting d’un film donné

3. Affichage des films sortis entre 2 années données

4. Affichage des films communs à 2 acteurs/actrices donnés.

5. Affichage des acteurs communs à 2 films donnés

6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting

7. Difficile : trouver le plus court chemin entre 2 acteurs (algorithme de la théorie des graphes à utiliser)

8. Fin de l’application

Les interactions avec l’utilisateur peuvent se faire avec le Scanner.