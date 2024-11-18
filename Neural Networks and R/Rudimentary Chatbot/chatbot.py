import random
import json
import pickle
import numpy as np

import nltk
from nltk.stem import WordNetLemmatizer

from keras.models import load_model

lemmatizer = WordNetLemmatizer()
intents = json.loads(open("intents.json").read()) # Load the file containing the intents 

words = pickle.load(open("words.pkl", "rb")) # Load the words.pkl file
classes = pickle.load(open("classes.pkl", "rb")) # Load the classes.pkl file
model = load_model("triage_chatbot.h5") # Load the model for the chatbot to use

# Cleaning up a sentence by tokenising it using nltk
def clean_up_sentence(sentence): 
    sentence_words = nltk.word_tokenize(sentence)
    sentence_words = [lemmatizer.lemmatize(word) for word in sentence_words]
    return sentence_words

# Creating a bag of words, which converts the input sentence into a numerical representation
def bag_of_words(sentence):
    sentence_words = clean_up_sentence(sentence)
    bag = [0] * len(words)
    for w in sentence_words:
        for i, word in enumerate(words):
            if word == w:
                bag[i] = 1
    return np.array(bag)

# Predict the class and attribute error threshold
def predict_class(sentence):
    BOW = bag_of_words(sentence)
    result = model.predict(np.array([BOW]))[0] # Predict class probabilities
    ERROR_THRESHOLD = 0.25 # Error threshold for probabilities to be considered insignificant
    results = [[i, r] for i, r in enumerate(result) if r > ERROR_THRESHOLD] # Store results in descending order. Ensuring that most probable class comes first

    results.sort(key = lambda x: x[1], reverse = True)
    return_list = []

    for r in results:
        return_list.append({"intent": classes[r[0]], "probability": str(r[1])})
    
    return return_list

# Find a response matching the intent
def get_response(tags_list, intents_json):
    tag = tags_list[0]["intent"]
    list_of_intents = intents_json["intents"]
    for i in list_of_intents:
        if i["tag"] == tag:
            result = random.choice(i["responses"])
            break
    return result

#print("Bot is running...") # Here for testing/Telling the user the bot is running...
print("Bot: PLEASE MAKE SURE YOU ARE NOT IN AN EMERGENCY!")
print("Bot: Hello, can you provide me with a list of your symptoms in a single sentence? Please be as accurate as you can so I can give you correct information.")
print("Please also note I am not a medical doctor, so I may be incorrect in my assessment.")

# while: True was used here to make the conversation end only when the program is stopped/killed
while True:
    message = input("User: ") # Give the user an input field to interact with the chatbot
    ints = predict_class(message)
    res = get_response(ints, intents) # Get response from the list of intents
    print("Bot: ", res) # Print the reply of the chatbot.