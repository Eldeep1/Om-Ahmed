package com.depogramming.omahmed.data.syncing.datasource;

import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class FireStoreDataSource {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String userId = auth.getCurrentUser().getUid();

    public Completable uploadAllUsersData(
            List<MealsPlanModel> plans,
            List<FavouriteMeals> favourites
    ) {

        return Completable.create(emitter -> {

            WriteBatch batch = db.batch();

            for (MealsPlanModel plan : plans) {
                DocumentReference ref = db.collection("users")
                        .document(userId)
                        .collection("mealPlans")
                        .document(plan.idMeal);

                batch.set(ref, plan);
            }

            for (FavouriteMeals fav : favourites) {
                DocumentReference ref = db.collection("users")
                        .document(userId)
                        .collection("favourites")
                        .document(fav.idMeal);

                batch.set(ref, fav);
            }

            batch.commit()
                    .addOnSuccessListener(unused -> {
                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    })
                    .addOnFailureListener(e -> {
                        if (!emitter.isDisposed()) {
                            emitter.onError(e);
                        }
                    });
        });
    }

}
