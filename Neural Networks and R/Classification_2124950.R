#installing required packages & libraries to make decision trees
library(rpart)
library(ggplot2)
library(class)

#reading data from seeds_dataset_class
seeds = read.csv('C:\\Users\\guidv\\OneDrive\\Computer Science 3rd Year\\CS3002\\Files\\seeds_dataset_class.csv', sep=",")
#randomizing seeds
seeds_rand = seeds[sample(150,150),]

#Splitting data into 70% training and 30% testing...
train_index <- sample(1:nrow(seeds_rand), 0.7 * nrow(seeds_rand))
seeds_train <- seeds_rand[train_index, ]
seeds_test <- seeds_rand[-train_index, ]

#Plotting the trained decision tree...
fit <- rpart(Class ~ ., data=seeds_train, method="class")

#Plotting the decision tree...
plot(fit, uniform=TRUE, main="Decision Tree for seeds data")
text(fit, use.n=TRUE, all=TRUE, cex=.8)

#Predicting the class labels for the test set...
treepred <- predict(fit, seeds_test, type="class")
#Ensure predictions are factors with the right levels
treepred <- factor(treepred, levels=unique(seeds_test$Class))


#Calculating the accuracy of the decision tree on the test set...
correct_predictions <- sum(treepred == seeds_test$Class)
accuracy <- correct_predictions / length(treepred)
cat("Accuracy before pruning:", accuracy, "\n")

#Pruning the decision tree...
cp_values <- c(0.01, 0.05, 0.1, 0.15, 0.2)  #These values can be adjusted as necessary...
cp_amount <- numeric(length(cp_values))

#Looping through each cp value, pruning the tree, predicting, and calculating accuracy for each...
for (i in 1:length(cp_values)) {
  #Pruning the tree at indexed cp value...
  fit_pruned <- prune(fit, cp=cp_values[i])
  #Predicting using the pruned tree...
  predict_pruned <- predict(fit_pruned, seeds_test, type="class")
  #Calculating the accuracy for pruned tree...
  cp_amount[i] <- sum(predict_pruned == seeds_test$Class) / length(predict_pruned)
  cat("Accuracy with pruning (cp =", cp_values[i], "):", cp_amount[i], "\n")
}

#Finding the cp value that provides the highest accuracy...
best_cp_i <- which.max(cp_amount)
best_cp_value <- cp_values[best_cp_i]

#Pruning the tree using the best cp value...
fit_best_prune <- prune(fit, cp=best_cp_value)

#Plotting the best pruned tree...
plot(fit_best_prune, uniform=TRUE, main=paste("Best Pruned Decision Tree (cp=", best_cp_value, ") with accuracy:", cp_amount[best_cp_i]))
text(fit_best_prune, use.n=TRUE, all=TRUE, cex=.8)

#Creating a scatter plot of the test set, coloured by the predicted class labels from the decision tree...
p <- ggplot(seeds_test, aes(x=Area, y=Perimeter, color=treepred)) + 
  geom_point() + 
  ggtitle("Scatterplot of Area vs Perimeter colored by Decision Tree predictions") + 
  labs(color = "Predicted Class")

print(p)

#Making KNN predictions for multiple values of k...
k_values <- c(1, 3, 5, 7, 9, 11, 13, 15)
knn_accuracies <- numeric(length(k_values))

#Looping through each value of k, predicting using KNN, and calculating accuracy...
for (i in 1:length(k_values)) {
  #Performing KNN prediction...
  knn_pred <- knn(train = seeds_train[, -1], test = seeds_test[, -1], cl = seeds_train$Class, k = k_values[i])
  #Calculating accuracy...
  knn_accuracies[i] <- sum(knn_pred == seeds_test$Class) / nrow(seeds_test)
  cat("Accuracy with KNN (k =", k_values[i], "):", knn_accuracies[i], "\n")
}
