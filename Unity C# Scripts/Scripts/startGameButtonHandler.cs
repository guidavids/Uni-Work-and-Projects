using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class startGameButtonHandler : MonoBehaviour
{

    string sceneName;

    // Start is called before the first frame update
    void Start()
    {
        Button button = GetComponent<Button>();
        button.onClick.AddListener(ChangeSceneOnClick);

        Scene currentScene = SceneManager.GetActiveScene();
        sceneName = currentScene.name;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void ChangeSceneOnClick()
    {
        if(sceneName == "Start Screen")
        {
            SceneManager.LoadScene("Narrative Screen");
        }
        else if(sceneName == "Narrative Screen")
        {
            SceneManager.LoadScene("Level 1");
        }
    }
}
