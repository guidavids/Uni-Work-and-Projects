#source('H:\\Desktop\\WK_R.r') #Source for when in labs

#seeds_dataset = read.csv('H:\\Desktop\\CS Year 3\\CS3002\\seeds_dataset.csv', sep=",") #Read for when in labs
#seeds_real = read.csv('H:\\Desktop\\CS Year 3\\CS3002\\seeds_real - Copy.csv', sep=",") #Read for when in labs

source('C:\\Users\\guidv\\Desktop\\CS3002 - Sources\\WK_R.r') #Source for when in labs

seeds_dataset = read.csv('C:\\Users\\guidv\\Desktop\\CS3002 - Sources\\seeds_dataset.csv', sep=",") #Read for when not in labs
seeds_real = read.csv('C:\\Users\\guidv\\Desktop\\CS3002 - Sources\\seeds_real.csv', sep=",") #Read for when not in labs

seeds_dataset = na.omit(seeds_dataset) #delete missing data...
seeds_dataset = scale(seeds_dataset) #standardize variables...

plot(seeds_dataset)
plot(seeds_real)

#Create hierarchical clustering for seeds_real
dr <- dist(seeds_real, method="euclidean") #Create euclidean distance matrix for seeds_real...
fit_seeds_real <- hclust(dr, method="average") #Make hierarchical clustering using "average" linkage
Kgroups <- cutree(fit_seeds_real, k=3) #Cut dendrogram into 3 clusters

#Concatenating linkage and defining k values for looping later...
linkage_values <- c("average", "single", "complete") #Concatenating linkage...
k_values <- 1:5 #Defining k values form 1 to 5

#Defining PCA values for plotting...
sd <- princomp(seeds_dataset)
seeds_2d <- predict(sd, seeds_dataset)[, 1:2]

#Setting up a matrix to store wk values for different linkage, k values, k-means...
wk_matrix <- matrix(0, nrow = length(k_values), ncol = length(linkage_values) + 1, 
  dimnames = list(k_values, c(linkage_values, "K-means")))

#Creating euclidean distance matrix for seeds_dataset...
d <- dist(seeds_dataset, method="euclidean")

#Creating a loop to iterate between linkage, create hierarchical clustering, store WK... 
for(value in linkage_values) {
  #Loop through k values
  for(k in k_values) {
    fit_seeds_dataset <- hclust(d, method=value) #Using different linkage...
    Hgroups <- cutree(fit_seeds_dataset, k=k) #Cutting tree to create clusters...
    wk_matrix[k, value] <- WK_R(Kgroups, Hgroups) #Calculate and store WK between real clusters and dataset clusters...
  }
}

#Creating a set of functions to identify the best outcome between linkage and k values...
best_k_i <- which(wk_matrix == max(wk_matrix), arr.ind=TRUE)[1] #Setting up best k index...
best_linkage_i <- which(wk_matrix == max(wk_matrix), arr.ind=TRUE)[2] #Setting up best linkage index...
best_k <- k_values[best_k_i]
best_linkage <- linkage_values[best_linkage_i]

#Clustering for best k and linkage...
fit_best <- hclust(dist(seeds_dataset, method="euclidean"))
cluster_best <- cutree(fit_best, k=best_k)

#Creating a scatter plot of the first two principal components...
plot(seeds_2d, col=cluster_best, main="2D Projection of best Hierarchical Clusters")

#Initialising a vector to store WK values for different k values...
wk_kmeans <- numeric(length(k_values))

#Create a loop for different k values for k-means clustering...
for(k in k_values) {
  kmeans_result <- kmeans(seeds_dataset, centers=k, nstart=25) #nstart will choose the best result from n configurations...
  kmeans_clusters <- kmeans_result$cluster
  wk_kmeans[k] <- WK_R(Kgroups, kmeans_clusters) #Calculate and store k-means...
}

#Storing WK values for hierarchical and k-means clustering...
wk_matrix[, "K-means"] <- wk_kmeans

#Print WK values for hierarchical and K-means clustering...
print(wk_matrix)

#Identify the best k for k-means with WK closest to 1...
best_k_m <- which.max(wk_kmeans)
print(paste("The best k-means result is: ", best_k_m, " with WK=", round(wk_matrix[best_k_i, best_linkage_i], 7)))
print(paste("The best results for Hierarchical are obtained with k=", best_k, "and linkage method:", best_linkage, "with WK =", round(wk_matrix[best_k_i, best_linkage_i], 7)))

#Plot K-means clustering for best k...
clusters_kmeans_best <- kmeans(seeds_dataset, centers=best_k_m, nstart=25)$cluster
plot(seeds_2d, col=clusters_kmeans_best, main=paste("2D Projection with Best K-means Clusters (k =", best_k_m, ")"))

#Plotting WK values for k-means...
plot(k_values, wk_kmeans, col="blue", type="b")

#Plotting clusters for best k and linkage...
plot(fit_best, main=paste("Best linkage is:", best_linkage, "."))
rect.hclust(fit_best, best_k, border="red")

#Creating heat map to map out all linkages against WK values, k-means... attempted to create another graph...
image(1:(length(linkage_values) + 1), k_values, t(wk_matrix), xlab="Clustering Method", ylab="k", xaxt="n", yaxt="n", main="Heatmap for WK Values")
axis(1, at=1:(length(linkage_values) + 1), labels=c(linkage_values, "K-means"))
axis(2, at=1:length(k_values), labels=k_values)