using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class retryButtonHandler : MonoBehaviour
{
    string sceneName;


    // Start is called before the first frame update
    void Start()
    {
        Scene currentScene = SceneManager.GetActiveScene();
        sceneName = currentScene.name;

        Button button = GetComponent<Button>();
        button.onClick.AddListener(ChangeSceneOnClick);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void ChangeSceneOnClick()
    {
        if(sceneName == "Game Over Scene")
        {
            SceneManager.LoadScene("Level 1");
        }
        else if(sceneName == "Game Over Scene 1")
        {
            SceneManager.LoadScene("Level 2");
        }
        else if(sceneName == "Game Over Scene 2")
        {
            SceneManager.LoadScene("Level 3");
        }
    }
}
