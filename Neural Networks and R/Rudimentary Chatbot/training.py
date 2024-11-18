import random # To pick a random response from a list.
import json # To read intents file
import pickle # For serializing
import numpy as np # To create numpy arrays

import nltk # Natural Language Toolkit
from nltk.stem import WordNetLemmatizer

from keras.models import Sequential # Model 
from keras.layers import Dense, Dropout # Dense layer for neuron. Dropout to precent overfitting
from keras.optimizers import SGD # Import to create a Stochastig Gradient Descent for optimization
import matplotlib.pyplot as plt # Import matplotlib to plot loss graph

# Loss and validation loss graph
def plot_loss(loss_values, val_loss_values):
    epochs = range(1, len(loss_values) + 1)
    plt.plot(epochs, loss_values, label = "Training Loss")
    plt.plot(epochs, val_loss_values, label = "Validation loss")
    plt.title("Training and Validation loss over Epochs")
    plt.xlabel("Epoch")
    plt.ylabel("Loss")
    plt.legend()
    plt.show()

lemmatizer = WordNetLemmatizer()

intents = json.loads(open("intents.json").read()) # Read the intents file

words = []
classes = []
documents = []

ignore_characters = ["?", "!", ".", ","] # Characters the chatbot will ignore form inputs

for intent in intents["intents"]:
    for pattern in intent["patterns"]:
        word_list = nltk.word_tokenize(pattern)
        words.extend(word_list)
        documents.append((word_list, intent["tag"]))
        if intent["tag"] not in classes:
            classes.append(intent["tag"])

words = [lemmatizer.lemmatize(word) for word in words if word not in ignore_characters]
words = sorted(set(words))

classes = sorted(set(classes))

pickle.dump(words, open("words.pkl", "wb"))
pickle.dump(classes, open("classes.pkl", "wb"))

training = []
output_empty = [0] * len(classes)

for document in documents:
    bag = []
    word_patterns = document[0]
    word_patterns = [lemmatizer.lemmatize(word.lower()) for word in word_patterns]
    for word in words:
        bag.append(1) if word in word_patterns else bag.append(0)

    output_row = list(output_empty)
    output_row[classes.index(document[1])] = 1
    training.append([bag, output_row])

random.shuffle(training)
training = np.array(training)
train_x = list(training[:, 0])
train_y = list(training[:, 1])

# Split data into training and validation sets
val_split = 0.2
val_samples = int(len(train_x) * val_split)
val_x = train_x[: val_samples]
val_y = train_y[: val_samples]
partial_train_x = train_x[val_samples: ]
partial_train_y = train_y[val_samples: ]

model = Sequential() # Create a sequential model 
model.add(Dense(128, input_shape=(len(train_x[0]),), activation="relu")) # Create dense layer and use relu activation
model.add(Dropout(0.5)) # 50% dropout rate
model.add(Dense(64, activation="relu"))
model.add(Dropout(0.5)) 
model.add(Dense(len(train_y[0]), activation="softmax")) # Softmax for output layer

sgd = SGD(lr=0.01, decay=1e-6, momentum=0.9, nesterov=True) # Stochastic Gradient Descent. 
model.compile(loss="categorical_crossentropy", optimizer=sgd, metrics=["accuracy"])  # Compile the model

history = model.fit(np.array(partial_train_x), np.array(partial_train_y), validation_data = (np.array(val_x), np.array(val_y)), epochs=200, batch_size=5, verbose=1)
model.save("triage_chatbot.h5", history)  # Saved model to the name in quotation marks

loss = history.history["loss"] # Get training loss
val_loss = history.history["val_loss"] # Get validation loss
plot_loss(loss, val_loss) # Plot loss graph

print("Done")